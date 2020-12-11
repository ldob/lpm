export interface IMember {

  id: number,
  username: string,
  name: string

}

export class Member implements IMember {

  constructor(
    public id: number,
    public username: string,
    public name: string
  ) { }

}
