
export interface ISkill{
    id : number,
    skill : string;
}

export interface IHobby{
    id : number;
    hobby : string;
}

export interface IUser{
    name :  string;
    fullName : string;
    qualification : string;
    skills : ISkill[]
    hobbies : IHobby[] 
}

export interface IInfoProp{
    name :  string;
    fullName : string;
    qualification : string;
}

