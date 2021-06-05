import { MedicineQuantity } from "./medicineQuantity";
import { Offer } from "./offer";

export class OrderList{
    public id : number;
    public medicines: MedicineQuantity[];
    public dueDate: Date;
    public offers: Offer[];
    
  }