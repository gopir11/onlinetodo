import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddToDoComponent } from './addToDo.component';

describe('AddToDoComponent', () => {
  let component: AddToDoComponent;
  let fixture: ComponentFixture<AddToDoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddToDoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddToDoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
