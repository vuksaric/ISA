# ISA

This web application represents a centralized pharmacy information system of pharmacies. Through this system unregistered users are able to look up pharmacies and medicines. Only registered users are able to book appointments with a pharmacist o dermatologist, to reserve medicines and to  subsribe to pharmacy promotions. Dermatologists and pharmacists also have access to the system, and can book new appointments, write appointment reports and prescriptions. The sistem is managed by both system and pharmacy administrators.

Final version of this project is on branch "develop".

Student 1 : Andjela Petrovic RA76/2017 <br> 
Student 2 : Vuk Saric RA78/2017 <br> 
Student 3 : Marjana Zalar RA79/2017 <br> 
Student 4 : Srdjan Velaga RA80/2017 <br> 

<h2> Build Maven Project  </h2>
1) Install IntelliJ IDEA from IntelliJ IDEA Official Page. <br> 
2) Open IntelliJ IDEA <br> 
3) Import project from ..\PharmacyWebApp\server\spring-boot-pharmacy <br> 
4) Right click on pom.xml -> Add as Maven Project <br> 
5) Wait for all dependencies to be reloaded <br> 
6) Right click on SpringBootPharmacyApplication.java -> Run <br> 

<h2> Build Angular Project  </h2>
1) Install NodeJs from NodeJs Official Page. <br>
2) Open Terminal <br>
3) Go to file project ..\ISA_project\src\main\resources\assets\my-app <br>
4) Run in terminal: npm install -g @angular/cli <br>
Step 1: npm install <br>
Step 2 : npm install ngx-toastr --save <br>
Step 3 : npm install @angular/animations --save <br>
Step 4 : npm install npm install @swimlane/ngx-charts --save <br>
Step 5 : npm install @agm/core --save <br>
And: ng serve
Navigate to: http://localhost:4200/

For testing purposes we advice you to use accounts with following emails: <br>
ppacijentovic@gmail.com //pacijent123 email password <br>
aapotekarovic@gmail.com //apotekar 123 email password <br>
dermatolog@gmail.com <br>
administrator@gmail.com <br>
