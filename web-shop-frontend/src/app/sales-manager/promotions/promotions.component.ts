import { Component, OnInit, ViewChild, TemplateRef, Renderer } from '@angular/core';
import { DataTableDirective } from 'angular-datatables';
import { Subject } from 'rxjs';
import { PromotionService } from '../services/promotion.service';
import { FormGroup, FormBuilder, Validators, FormArray, FormControl } from '@angular/forms';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { ItemCategoryService } from '../services/item-category.service';

@Component({
  selector: 'app-promotions',
  templateUrl: './promotions.component.html',
  styleUrls: ['./promotions.component.css']
})
export class PromotionsComponent implements OnInit {
  
  promotions: any[] = [];

  listenerFn: any;

  dtOptions: DataTables.Settings = {}

  @ViewChild(DataTableDirective, { static: false })
  datatableElement: DataTableDirective;

  dtTrigger: Subject<any> = new Subject<any>();

  //modal setting
  
  @ViewChild("content", { static: true }) modalContent: TemplateRef<any>;
  closeResult: string;
  errorMessage: string;

  newPromotionForm: FormGroup;

  itemCategories: any[] = [];

  constructor(
    private promotionService: PromotionService,
    private renderer: Renderer,
    private modalService: NgbModal,
    private formBuilder: FormBuilder,
    private itemCategoryService: ItemCategoryService
  ) { }

  ngOnInit() {

    this.newPromotionForm = this.formBuilder.group({
      id: ['', Validators.required],
      startTime: ['', Validators.required],
      startDate: ['', Validators.required],
      endDate: ['', Validators.required],
      endTime: ['', Validators.required],
      percentageDiscount: ['', Validators.required],
      name: ['', Validators.required],
      categories: new FormArray([])
    })


    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 10,
      searching: true,
      rowId: 'id',
      columns: [{
        title: 'ID',
        data: 'id',
        visible: false
      }, {
        title: 'Name',
        data: 'name'
      },{
        title: 'Percentage Discount',
        data: 'percentageDiscount'
      },{
        title: 'Start date',
        data: 'startDate'
      },{
        title: 'End date',
        data: 'endTime'
      },
      {
        title: 'Action',
        render: function (data: any, type: any, full: any) {
          return '<button class="waves-effect btn btn-warning btn-sm" title="Refill stocks" clicked-id="' + full.id + '"><img src="../../../../assets/img/pencil.svg" clicked-id="' + full.id + '" title="Refill stocks"></button>';
        }
      }
      ]

    }

    this.promotionService.getAllPromotions().subscribe(
      response => {
        this.promotions = response.body;
        this.dtTrigger.next();
      }
    );

    this.itemCategoryService.getAllCategories().subscribe(
      response => {
        this.itemCategories = response.body;
        this.itemCategories.forEach((o, i) => {
          const control = new FormControl();
          (this.newPromotionForm.controls.categories as FormArray).push(control);
        })
      }
    )



  }

  ngAfterViewInit(): void {

    this.listenerFn = this.renderer.listenGlobal('document', 'click', (event) => {
      if (event.target.hasAttribute("clicked-id")) {
        let id: number = event.target.getAttribute("clicked-id");
        
        let promotion = this.promotions.filter(promotion => promotion.id == id);

        this.newPromotionForm.controls["id"].setValue(id);
        this.newPromotionForm.controls["name"].setValue(promotion[0].name);
        this.newPromotionForm.controls["percentageDiscount"].setValue(promotion[0].percentageDiscount);

        this.openModal();
      }

    });

    setTimeout(() => {
      this.datatableElement.dtInstance.then((dtInstance: DataTables.Api) => {
        dtInstance.columns().every(function () {
          const that = this;
          $('input', this.footer()).on('keyup change', function () {
            if (that.search() !== this['value']) {
              that
                .search(this['value'])
                .draw();
            }
          });
        });
      });
    }, 1000);
  }

  openModal() {
    this.modalService.open(this.modalContent, { ariaLabelledBy: 'modal-basic-title' }).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    })
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  onSubmit() {
    let dateHoursFrom = this.newPromotionForm.controls["startTime"].value.hour;
    let dateHoursFromFormatted = ("0" + dateHoursFrom).slice(-2);

    let dateMinutesFrom = this.newPromotionForm.controls["startTime"].value.minute;
    let dateMinutesFromFormatted = ("0" + dateMinutesFrom).slice(-2);

    let monthFrom = this.newPromotionForm.controls["startDate"].value.month;
    let monthFromFormatted = ("0" + monthFrom).slice(-2);

    let dayFrom = this.newPromotionForm.controls["startDate"].value.day;
    let dayFromFormatted = ("0" + dayFrom).slice(-2);

    let dateStringFrom = this.newPromotionForm.controls["startDate"].value.year + '-' + monthFromFormatted +
      '-' + dayFromFormatted + ' ' + dateHoursFromFormatted + ':' + dateMinutesFromFormatted + ':00';

    let dateHoursTo = this.newPromotionForm.controls["endTime"].value.hour;
    let dateHoursToFormatted = ("0" + dateHoursTo).slice(-2);

    let dateMinutesTo = this.newPromotionForm.controls["endTime"].value.minute;
    let dateMinutesToFormatted = ("0" + dateMinutesTo).slice(-2);


    let monthTo = this.newPromotionForm.controls["endDate"].value.month;
    let monthToFormatted = ("0" + monthTo).slice(-2);

    let dayTo = this.newPromotionForm.controls["endDate"].value.day;
    let dayToFormatted = ("0" + dayTo).slice(-2);

    let dateStringTo = this.newPromotionForm.controls["endDate"].value.year + '-' + monthToFormatted +
      '-' + dayToFormatted + ' ' + dateHoursToFormatted + ':' + dateMinutesToFormatted + ':00';


    const selectedCategoriesIds = this.newPromotionForm.value.categories
      .map((v, i) => (v ? this.itemCategories[i].id : null))
      .filter(v => v !== null);
    console.log(selectedCategoriesIds);


    var promotion = new Object;
    promotion["startTime"] = dateStringFrom;
    promotion["endTime"] = dateStringTo;
    promotion["percentageDiscount"] = this.newPromotionForm.controls["percentageDiscount"].value;
    promotion["name"] = this.newPromotionForm.controls["name"].value;
    promotion["categoryIds"] = selectedCategoriesIds;

    console.log(promotion);

    this.promotionService.updatePromotion(this.newPromotionForm.controls["id"].value, promotion).subscribe(
      response => {
        this.modalService.dismissAll();
        window.location.reload();
      }
    )
  }


}
