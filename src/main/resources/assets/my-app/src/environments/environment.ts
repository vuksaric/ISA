// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  base_url: 'http://localhost:8080',
  auth_url: 'http://localhost:8080/auth',
  user_url: 'http://localhost:8080/isa/user',
  pharmacist_url: 'http://localhost:8080/pharmacist',
  patient_url: 'http://localhost:8080/patient',
  userProfile_url: 'http://localhost:8080/user',
  pharmacy_url: 'http://localhost:8080/pharmacy',
  examination_url: 'http://localhost:8080/examination',
  patientChart_url : 'http://localhost:8080/patientChart',
  medicine_url: 'http://localhost:8080/medicine'
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
