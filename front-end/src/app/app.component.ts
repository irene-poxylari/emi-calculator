import {Component, OnInit} from '@angular/core';
import {DataService} from "./data-service";
import {Input} from "./input";
import {NgForm, Validators} from "@angular/forms";
import {HttpErrorResponse} from "@angular/common/http";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  title = 'EMICalculator-FrontEnd';
  public inputs: Input[] | undefined;
  public result: number | undefined;
  public isCalculated = true;

  inputForm: FormGroup = new FormGroup({
    loanAmount: new FormControl(null, [Validators.min(0)]),
    yearlyInterestRate: new FormControl(null,),
    loanTermsInYears: new FormControl(null,),
    emailAddress: new FormControl(null),
    result: new FormControl(null)
  });

  constructor(private dataService: DataService) {
  }

  public getInputs(): void {
    this.dataService.getInputs().subscribe(
      (response: Input[]) => {
        this.inputs = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  ngOnInit(): void {
    this.getInputs();
  }

  calculate() {
    const object = this.inputForm.value
    if (this.inputForm?.valid) {
      this.dataService.calculateEmi(object).subscribe(
        (response: number) => {
          this.result = response;
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      )
      //if the calculation is done the save button will be enabled
      this.isCalculated = false;
    } else {
      this.validateAllFormFields(this.inputForm);

    }
  }

  validateAllFormFields(formGroup: FormGroup) {
    Object.keys(formGroup.controls).forEach(field => {
      const control = formGroup.get(field);
      if (control instanceof FormControl) {
        control.markAsDirty({onlySelf: true});
      } else if (control instanceof FormGroup) {
        this.validateAllFormFields(control);
      }
    });
  }

  save() {
    const object = this.inputForm.value

    if (this.inputForm?.valid) {
      this.dataService.addInput(object).subscribe(
        (response: Input) => {
            console.log(response);
            alert("The input has been successfully saved")
            this.inputForm.reset();
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      )
      //if the calculation is done the save button will be enabled
      this.isCalculated = true;
    }
  }
}
