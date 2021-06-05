import { User } from "./user";

export class Offer{
    public id : number;
    public price: number;
    public dueDate: Date;
    public supplier: User; //videti da li ce ovo uopste da radi sa ovim
    public status: string;
  }