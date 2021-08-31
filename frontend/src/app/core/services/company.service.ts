import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Company } from '../model/Company';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  constructor(private http : HttpClient) { }

  getCompanies() : Observable<any> {
    return this.http.get(`${environment.urlApi}companies/getCompanies`);
  }

  deleteCompany(nit: string) : Promise<any> {
    return this.http.delete(`${environment.urlApi}companies/deleteCompany/${nit}`).toPromise();
  }

  addCompany(company: Company) : Promise<any>{
    return this.http.post(`${environment.urlApi}companies/createCompany/`, company).toPromise();
  }

  updateCompany(nit : string, company: Company) : Promise<any> {
    return this.http.put(`${environment.urlApi}companies/updateCompany/${nit}`, company).toPromise();
  }

}
