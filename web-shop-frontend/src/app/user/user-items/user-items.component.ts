import { Component, OnInit, ViewChild, Renderer } from '@angular/core';
import { UserItemsService } from './user-items.service';
import { DataTableDirective } from 'angular-datatables';
import { Subject } from 'rxjs';
import { Router } from '@angular/router';
import { Item } from 'src/app/domain/item';

@Component({
  selector: 'app-user-items',
  templateUrl: './user-items.component.html',
  styleUrls: ['./user-items.component.css']
})
export class UserItemsComponent implements OnInit {

  listenerFn: any;

  dtOptions: DataTables.Settings = {}

  @ViewChild(DataTableDirective, {static: false})
  datatableElement: DataTableDirective;

  dtTrigger: Subject<any> = new Subject<any>();


  items: Item[] = [];

  min: number;
  max: number;
  
  constructor(
    private itemsService: UserItemsService,
    private router: Router,
    private renderer: Renderer
  ) { }

  ngOnInit() {
    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 10,
      searching: true,
      rowId: 'id',
      columns: [ {
        title: 'ID',
        data: 'id'
      },{
        title: 'Name',
        data: 'name'
      }, {
        title: 'Price',
        data: 'price'
      },{
        title: 'Category',
        data: 'itemCategory.name'
      },{
        title: 'Promotion',
        data: 'item.promotion'
      },{
        title: 'Quantity',
        data: 'null'
      },
      {
        title: 'Buy',
        render: function (data: any, type: any, full: any) {
          return '<button class="waves-effect btn btn-secondary btn-sm" title="Buy item" clicked-id="' + full.id + '"><img src="../../../../assets/img/car-plus.svg" clicked-id="' + full.id + '" title="Add administrator"></button> ';
        }
      }
    ]
    }

    
    this.itemsService.getAll().subscribe(
      resp => {
        console.log(resp);
        this.items = resp.body;
        this.dtTrigger.next();
      }
    )
  }

  filter() {
    console.log(this.items[2]);
    this.items = this.items.filter(item => item.price < 3000 && item.price > 121);
  }

  ngAfterViewInit(): void {

    this.listenerFn = this.renderer.listen('document', 'click', (event) => {
      if (event.target.hasAttribute("clicked-id")) {
        let id = event.target.getAttribute("clicked-id");

        var quantity = (<HTMLInputElement>document.getElementById(id + "-input")).value;
        console.log("id: " + id + ", quantity " + quantity);
      }
    });

    $.fn['dataTable'].ext.search.push((settings, data, dataIndex) => {
      const id = parseFloat(data[2]) || 0; // use data for the id column
      if ((isNaN(this.min) && isNaN(this.max)) ||
        (isNaN(this.min) && id <= this.max) ||
        (this.min <= id && isNaN(this.max)) ||
        (this.min <= id && id <= this.max)) {
        return true;
      }
      return false;
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

  ngOnDestroy() {
    if(this.listenerFn) {
      this.listenerFn();
    }
  }

  filterById(): void {
    this.datatableElement.dtInstance.then((dtInstance: DataTables.Api) => {
      dtInstance.draw();
    });
  }

}
