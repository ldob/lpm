export interface IUser {

  id: number,
  username: string,
  email: string

}

export class User implements IUser {

  // @ts-ignore
  constructor()
  constructor(
    public id: number,
    public username: string,
    public email: string
  ) { }

}
