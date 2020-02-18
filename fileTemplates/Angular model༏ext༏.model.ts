#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};
${NAME}
${SINGLE_ENTITY}
#end
#parse("File Header.java")

#set($ENTITY=$SINGLE_ENTITY + "Entity")

import { Deserializable } from "../deserializable.model";
import { GenericRuleEntity } from "../genericrule/genericrule.model";

export class ${ENTITY} extends GenericRuleEntity implements Deserializable{
  public id: number;
  
  // TODO GSP CREATE FIELDS

  constructor(  ) { super(); }

  isValid() {
    // TODO GSP MODIFY
    if (this.id){
      return true;
    }
    return false;
  }
}
