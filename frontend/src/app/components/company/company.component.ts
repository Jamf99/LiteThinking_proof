import {Component, ViewChild, OnInit, AfterViewInit} from '@angular/core';
import {MatTable, MatTableDataSource} from '@angular/material/table';
import {Company} from '../../core/model/Company';
import {CompanyService} from '../../core/services/company.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.css']
})
export class CompanyComponent implements OnInit {

  listData!: MatTableDataSource<Company>;
  displayedColumns: string[] = ['nit', 'name', 'address', 'phone', 'actions'];
  searchKey!: string;

  constructor(private companyService : CompanyService) { }

  ngOnInit(): void {
    this.loadCompanies();
  }

  onSearchClear() {
    this.searchKey = "";
    this.applyFilter();
  }

  applyFilter() {
    this.listData.filter = this.searchKey.trim().toLowerCase();
  }

  loadCompanies() : void {
    this.companyService.getCompanies().subscribe(
      (res) => {
        this.listData = new MatTableDataSource(res);
      }
    )
  }

  async addCompany() {
    var nit, name, address, phone = '';
    const { value: formValues } = await Swal.fire({
      title: 'Add a company',
      html:
        "<h4 style='margin-bottom:-1%;'>NIT</h4>"+
        '<input id="nit" class="swal2-input" type="number">' +
        "<h4 style='margin-bottom:-1%; margin-top:4%'>Name</h4>"+
        '<input id="name" class="swal2-input" type="text">' +
        "<h4 style='margin-bottom:-1%; margin-top:4%'>Address</h4>"+
        '<input id="address" class="swal2-input" type="text">'+
        "<h4 style='margin-bottom:-1%; margin-top:4%'>Phone</h4>"+
        '<input id="phone" class="swal2-input" type="number">',
      focusConfirm: false,
      preConfirm: () => {
        return [
          nit = (<HTMLInputElement>document.getElementById('nit')).value,
          name = (<HTMLInputElement>document.getElementById('name')).value,
          address = (<HTMLInputElement>document.getElementById('address')).value,
          phone = (<HTMLInputElement>document.getElementById('phone')).value
        ]
      }
    })
    
    if (formValues) {
      if(nit != null && name != null && address != null && phone != null) {
        var company = {
          nit : nit,
          name: name,
          address : address,
          phone : phone
        }
        this.companyService.addCompany(company).then((res) => {
          Swal.fire("Company added", "", "success");
          this.loadCompanies();
        })
        .catch((err) => {
          var error = err.error
          Swal.fire("Error!", error.toString(), "error")
        }) 
      }else {
        Swal.fire("Empty fields", "Please complete all the fields", "warning");
      }
    }
  }

  async updateCompany(row: Company) {
    var nit, name, address, phone = '';
    const { value: formValues } = await Swal.fire({
      title: "Update company ' "+row.name+" '",
      html:
        "<h4 style='margin-bottom:-1%;'>NIT</h4>"+
        "<input id='nit' class='swal2-input' type='number' value="+row.nit+">" +
        "<h4 style='margin-bottom:-1%; margin-top:4%'>Name</h4>"+
        "<input id='name' class='swal2-input' type='text' value="+row.name+">" +
        "<h4 style='margin-bottom:-1%; margin-top:4%'>Address</h4>"+
        "<input id='address' class='swal2-input' type='text' value="+row.address+">" +
        "<h4 style='margin-bottom:-1%; margin-top:4%'>Phone</h4>"+
        "<input id='phone' class='swal2-input' type='number' value="+row.phone+">",
      focusConfirm: false,
      preConfirm: () => {
        return [
          nit = (<HTMLInputElement>document.getElementById('nit')).value,
          name = (<HTMLInputElement>document.getElementById('name')).value,
          address = (<HTMLInputElement>document.getElementById('address')).value,
          phone = (<HTMLInputElement>document.getElementById('phone')).value
        ]
      }
    })
    
    if (formValues) {
      if(nit != null && name != null && address != null && phone != null) {
        var company = {
          nit : nit,
          name: name,
          address : address,
          phone : phone
        }
        this.companyService.updateCompany(row.nit, company).then((res) => {
          Swal.fire("Company updated", "", "success");
          this.loadCompanies();
        })
        .catch((err) => {
          var error = err.error
          Swal.fire("Error!", error.toString(), "error")
        }) 
      }else {
        Swal.fire("Empty fields", "Please complete all the fields", "warning");
      }
    }
  }

  deleteCompany(nit: string) : void {
    Swal.fire({
      title: "Are you sure?",
      text: "You won't be able to revert this!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#009900',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
      if (result.isConfirmed) {
        this.companyService.deleteCompany(nit).then(
          () => {
            Swal.fire(
              'Deleted!',
              'The company has been deleted',
              'success'
            );
            this.loadCompanies();
          },
          function() {
            Swal.fire(
              'Error',
              "Couldn't delete company",
              'error'
            )
          }
        )  
      }
    })
  }

}
