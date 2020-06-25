import { Component, OnInit, ViewChild, Renderer } from '@angular/core';
import { DataTableDirective } from 'angular-datatables';
import { Subject } from 'rxjs';
import { OrderService } from '../services/order.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {

  orders: any[] = [];

  listenerFn: any;

  dtOptions: DataTables.Settings = {}

  @ViewChild(DataTableDirective, { static: false })
  datatableElement: DataTableDirective;

  dtTrigger: Subject<any> = new Subject<any>();

  //modal setting
  


  constructor(
    private renderer: Renderer,
    private orderService: OrderService) { }

  ngOnInit() {


    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 10,
      searching: true,
      rowId: 'id',
      columns: [{
        title: 'ID',
        data: 'id'
      }, {
        title: 'State',
        data: 'orderState'
      },
      {
        title: 'Action',
        render: function (data: any, type: any, full: any) {
          return '<button class="waves-effect btn btn-success btn-sm" title="Approve order" approve-clicked-id="' + full.id + '"><img src="../../../../assets/img/check.svg" approve-clicked-id="' + full.id + '" title="Approve order"></button><button class="waves-effect btn btn-danger btn-sm ml-2" title="Cancel order" cancel-clicked-id="' + full.id + '"><img src="../../../../assets/img/x.svg" cancel-clicked-id="' + full.id + '" title="Cancel order"></button>';
        }
      }
      ]

    }
    this.orderService.getAllOrders().subscribe(
      response => {
        this.orders = response.body;
        this.dtTrigger.next();
      }
    )
  }

  ngAfterViewInit(): void {

    this.listenerFn = this.renderer.listenGlobal('document', 'click', (event) => {
      if (event.target.hasAttribute("approve-clicked-id")) {
        let id: number = event.target.getAttribute("approve-clicked-id");

        this.orderService.approveOrder(id).subscribe(
          response => {
            console.log(response);
            window.location.reload();
          }
        )
        
      } else if(event.target.hasAttribute("cancel-clicked-id")) {
        let id: number = event.target.getAttribute("cancel-clicked-id");

        this.orderService.cancelOrder(id).subscribe(
          response => {
            console.log(response);
            window.location.reload();
          }
        )
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


}
