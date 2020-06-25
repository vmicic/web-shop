import { Component, OnInit, ViewChild, Renderer, TemplateRef } from '@angular/core';
import { DataTableDirective } from 'angular-datatables';
import { Subject } from 'rxjs';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UserCategoryService } from '../services/user-category.service';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-user-category',
  templateUrl: './user-category.component.html',
  styleUrls: ['./user-category.component.css']
})
export class UserCategoryComponent implements OnInit {

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
    private userCategoryService: UserCategoryService) { }

  ngOnInit() {
    this.newCategoryForm = this.formBuilder.group({
      id: ['', Validators.required],
      name: ['', Validators.required]
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
      },
      {
        title: 'Action',
        render: function (data: any, type: any, full: any) {
          return '<button class="waves-effect btn btn-warning btn-sm" title="Refill stocks" clicked-id="' + full.id + '"><img src="../../../../assets/img/pencil.svg" clicked-id="' + full.id + '" title="Refill stocks"></button>';
        }
      }
      ]

    }
    this.userCategoryService.getAllCategories().subscribe(
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

  }
  
  ngOnDestroy()  {
    this.modalService.dismissAll();
  }
}
