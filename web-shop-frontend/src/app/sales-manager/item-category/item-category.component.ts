import { Component, OnInit, Renderer, TemplateRef, ViewChild, OnDestroy } from '@angular/core';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { FormBuilder, FormGroup, Validators, FormControl, FormArray } from '@angular/forms';
import { ItemCategoryService } from '../services/item-category.service';
import { Subject } from 'rxjs';
import { DataTableDirective } from 'angular-datatables';

@Component({
  selector: 'app-item-category',
  templateUrl: './item-category.component.html',
  styleUrls: ['./item-category.component.css']
})
export class ItemCategoryComponent implements OnInit, OnDestroy {

  categories: any[] = [];

  listenerFn: any;

  dtOptions: DataTables.Settings = {}

  @ViewChild(DataTableDirective, { static: false })
  datatableElement: DataTableDirective;

  dtTrigger: Subject<any> = new Subject<any>();

  //modal setting
  
  @ViewChild("editCategory", { static: true }) modalContent: TemplateRef<any>;
  closeResult: string;
  errorMessage: string;

  newCategoryForm: FormGroup;

  constructor(
    private renderer: Renderer,
    private modalService: NgbModal,
    private formBuilder: FormBuilder,
    private itemCategoryService: ItemCategoryService) { }

  ngOnInit() {
    this.newCategoryForm = this.formBuilder.group({
      id: ['', Validators.required],
      name: ['', Validators.required],
      maxPercentageDiscount: ['', Validators.required]
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
        title: 'Max Percentage Discount',
        data: 'maxPercentageDiscount'
      },
      {
        title: 'Action',
        render: function (data: any, type: any, full: any) {
          return '<button class="waves-effect btn btn-warning btn-sm" title="Refill stocks" clicked-id="' + full.id + '"><img src="../../../../assets/img/pencil.svg" clicked-id="' + full.id + '" title="Refill stocks"></button>';
        }
      }
      ]

    }
    this.itemCategoryService.getAllCategories().subscribe(
      response => {
        this.categories = response.body;
        this.dtTrigger.next();
      }
    )
  }

  ngAfterViewInit(): void {

    this.listenerFn = this.renderer.listenGlobal('document', 'click', (event) => {
      if (event.target.hasAttribute("clicked-id")) {
        let id: number = event.target.getAttribute("clicked-id");
        
        let category = this.categories.filter(category => category.id == id);

        this.newCategoryForm.controls["id"].setValue(id);
        this.newCategoryForm.controls["name"].setValue(category[0].name);
        this.newCategoryForm.controls["maxPercentageDiscount"].setValue(category[0].maxPercentageDiscount);

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
    this.itemCategoryService.update(this.newCategoryForm.controls["id"].value, this.newCategoryForm.value).subscribe(
      response => {
        this.modalService.dismissAll();
        window.location.reload();
      }
    )
  }
  
  ngOnDestroy()  {
    this.modalService.dismissAll();
  }
}
