import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReplenishComponent } from './replenish.component';

describe('ReplenishComponent', () => {
  let component: ReplenishComponent;
  let fixture: ComponentFixture<ReplenishComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReplenishComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReplenishComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
