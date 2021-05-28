export class Ingridient{
    name: string
}

export class Medicine{
    public name : string;
    public type : string;
    public shape : string;
    public ingredients : Ingridient[];
    public manufacturer : string;
    public issuingMode : string;
    public replacements : number[];
    public notes : string;
    public id: number;
}