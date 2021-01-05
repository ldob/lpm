import {IMember} from "./member";

export interface IProjectTodo {

  id: number,
  date: Date,
  status: string,
  description: string,
  assignedMember: IMember,
  edit: boolean

}

export class ProjectTodo implements IProjectTodo {

  // @ts-ignore
  constructor()
  constructor(
    public id: number,
    public date: Date,
    public status: string,
    public description: string,
    public assignedMember: IMember,
    public edit: boolean
  ) { }

}
