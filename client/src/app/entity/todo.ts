import {IMember} from "./member";

export interface ITodo {

  id: number,
  projectId: number,
  projectName: string,
  user: IMember,
  dueDate: Date,
  status: string,
  description: string

}

export class Todo implements ITodo {

  // @ts-ignore
  constructor()
  constructor(
    public id: number,
    public projectId: number,
    public projectName: string,
    public user: IMember,
    public dueDate: Date,
    public status: string,
    public description: string
  ) { }

}
