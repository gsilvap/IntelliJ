#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};
${NAME}
    ${SINGLE_ENTITY}
#end
#parse("File Header.java")

#set($COMPONENT_NAME=$SINGLE_ENTITY + "Component")

import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import {${COMPONENT_NAME}} from "./${NAME}.component";

describe('${COMPONENT_NAME}', () => {
  let component: ${COMPONENT_NAME};
  let fixture: ComponentFixture<${COMPONENT_NAME}>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ${COMPONENT_NAME} ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(${COMPONENT_NAME});
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
