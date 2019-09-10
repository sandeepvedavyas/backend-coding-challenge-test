insert into mobile_customer(id,first_name,last_name,company_name,email)
values(10001,'Customer1','lastname123','mobile.de','test@gmail.com');
insert into mobile_customer(id,first_name,last_name,company_name,email)
values(10002,'Customer2','lastname123','ebay.de','mno@gmail.com');
insert into mobile_customer(id,first_name,last_name,company_name,email)
values(10003,'Customer3','lastname123','other.de','abc@gmail.com');
insert into mobile_customer(id,first_name,last_name,company_name,email)
values(10004,'Customer4','lastname456','test.de','xyz@gmail.com');
insert into mobile_ad(id,mobile_customer_id,category,description,make,model,price)
values(20001,10001,'BMW','test BMW car data','518 d Business Eur6 Xenon','car',30000);
insert into mobile_ad(id,mobile_customer_id,category,description,make,model,price)
values(20002,10001,'Volkswagen','Volkswagen test  data','Golf VII Estate 2.0 TDI2','car',25000);
insert into mobile_ad(id,mobile_customer_id,category,description,make,model,price)
values(20003,10001,'Tabbert Da','test truck data','Vinci 490 TD promotional','truck',21950);