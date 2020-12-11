import {IMember} from "./member";

export interface IProject {

  id: number,
  name: string,
  description: string,
  priority: string,
  assignedMembers: IMember[],
  startDate: Date,
  plannedEndDate: Date,
  endDate: Date,
  resourceBudget: number,
  status: string

}

export class Project implements IProject {

  // @ts-ignore
  constructor()
  constructor(
    public id: number,
    public name: string,
    public description: string,
    public priority: string,
    public assignedMembers: IMember[],
    public startDate: Date,
    public plannedEndDate: Date,
    public endDate: Date,
    public resourceBudget: number,
    public status: string
  ) { }

}
