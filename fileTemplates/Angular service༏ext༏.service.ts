#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};
${NAME}
${SINGLE_ENTITY}
#end
#parse("File Header.java")

#set($SERVICE_NAME=$SINGLE_ENTITY + "Service")
#set($ENTITY=$SINGLE_ENTITY + "Entity")

import { HttpClient } from '@angular/common/http';
import { responseMessages } from '../.utils/fields';
import { Injectable } from '@angular/core';
import { catchError, map, tap } from "rxjs/operators";
import { environment as env } from "../../environments/environment";
import { ${ENTITY} } from "./${NAME}.model";
import { NewApiService } from "../.services/newapi.service";
import { MessageService } from "primeng/api";

@Injectable({
    providedIn: 'root'
})
export class ${SERVICE_NAME} extends NewApiService {
    API_URL       = env.apiUrl;
    SERVICE_NAME  = '${NAME}';
    ENTITY_NAME   = ${ENTITY};

    constructor (public http: HttpClient, public messageService: MessageService) {
        super(http, messageService);
    }

    // ENTITY ENDPOINTS
    // GET BY ID
    get${SINGLE_ENTITY}(id: number) {
      return this.http.get<${ENTITY}>(`${this.API_URL}/${this.SERVICE_NAME}/`+id).pipe(
        map(data => new ${ENTITY}().deserialize(data)),
        catchError(this.handleError<any>(`${this.ENTITY_NAME} not found`))
      );
    }

    // PUT BY ID
    put${SINGLE_ENTITY}(id: number, body: object) {
      return this.http.put(`${this.API_URL}/${this.SERVICE_NAME}/`+id, body).pipe(
        tap(_ => console.log(responseMessages.set_success_msg)),
        catchError(this.handleError<any>(responseMessages.set_failure_msg))
      );
    }

    // DELETE BY ID
    delete${SINGLE_ENTITY}(id: number) {
      return this.http.delete(`${this.API_URL}/${this.SERVICE_NAME}/`+id).pipe(
        tap(_ => console.log(responseMessages.delete_success_msg)),
        catchError(this.handleError<any>(responseMessages.delete_failure_msg))
      );
    }

    // ENTITY BULK OPERATIONS
    // GET
    get${SINGLE_ENTITY}s() {
      return this.http.get<${ENTITY}[]>(`${this.API_URL}/${this.SERVICE_NAME}`).pipe(
        map(data => data.map(data => new ${ENTITY}().deserialize(data))),
        catchError(this.handleError<any>(`${this.ENTITY_NAME} not found`))
      );
    }

    // POST
    post${SINGLE_ENTITY}s(id: number, body: object) {
      return this.http.post(`${this.API_URL}/${this.SERVICE_NAME}/`+id, body).pipe(
        tap(_ => console.log(responseMessages.set_success_msg)),
        catchError(this.handleError<any>(responseMessages.set_failure_msg))
      );
    }

    // PUT
    put${SINGLE_ENTITY}s(body: object) {
      return this.http.put(`${this.API_URL}/${this.SERVICE_NAME}`, body).pipe(
        tap(_ => console.log(responseMessages.set_success_msg)),
        catchError(this.handleError<any>(responseMessages.set_failure_msg))
      );
    }

    // DELETE
    delete${SINGLE_ENTITY}s(id: number[]) {
      return this.http.delete(`${this.API_URL}/${this.SERVICE_NAME}/`+id).pipe(
        tap(_ => console.log(responseMessages.delete_success_msg)),
        catchError(this.handleError<any>(responseMessages.delete_failure_msg))
      );
    }
    // END ENTITY BULK OPERATIONS - END ENTITY ENDPOINTS
}
