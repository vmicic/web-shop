import { Component, OnInit, ViewChild, Renderer, TemplateRef } from '@angular/core';
import { DataTableDirective } from 'angular-datatables';
import { Subject } from 'rxjs';
import { ItemService } from '../services/item.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';


@Component({
  selector: 'app-items-refill',
  templateUrl: './items-refill.component.html',
  styleUrls: ['./items-refill.component.css']
})
export class ItemsRefillComponent implements OnInit {

  items: any[] = [];
  classesObs$: Subject<any[]> = new Subject<any[]>();

  listenerFn: any;

  dtOptions: DataTables.Settings = {}

  @ViewChild(DataTableDirective, { static: false })
  datatableElement: DataTableDirective;

  dtTrigger: Subject<any> = new Subject<any>();

  //modal setting
  
  @ViewChild("content", { static: true }) modalContent: TemplateRef<any>;
  closeResult: string;
  errorMessage: string;

  addAmountForm: FormGroup;

  constructor(
    private itemService: ItemService,
    private renderer: Renderer,
    private modalService: NgbModal,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {

    this.addAmountForm = this.formBuilder.group({
      id: ['', Validators.required],
      amount: ['', Validators.required]
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
        title: 'Reason',
        data: 'refillReason'
      },
      {
        title: 'Action',
        render: function (data: any, type: any, full: any) {
          return '<button class="waves-effect btn btn-warning btn-sm" title="Refill stocks" clicked-id="' + full.id + '"><img src="../../../../assets/img/pencil.svg" clicked-id="' + full.id + '" title="Refill stocks"></button>';
        }
      }
      ]

    }

    this.itemService.getItemsRefill().subscribe(
      response => {
        this.items = response.body;
        this.dtTrigger.next();
      }
    );



  }

  ngAfterViewInit(): void {

    this.listenerFn = this.renderer.listenGlobal('document', 'click', (event) => {
      if (event.target.hasAttribute("clicked-id")) {
        let id: number = event.target.getAttribute("clicked-id");

        console.log("clicked id " + id);
        this.addAmountForm.controls["id"].setValue(id);
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

  addAmount() {
    console.log(this.addAmountForm.value);

    this.itemService.refillItem(this.addAmountForm.controls["id"].value, this.addAmountForm.controls["amount"].value).subscribe(
      response => {
        console.log(response);
        this.modalService.dismissAll();
        window.location.reload();
      }
    )
  }



}
