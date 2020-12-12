import {IMember} from "./member";

export interface IProject {

  id: number,
  name: string,
  description: string,
  priority: string,
  assignedMembers: Map<string,IMember[]>,
  startDate: Date,
  plannedEndDate: Date,
  endDate: Date,
  resourceBudget: number,
  resourceUsed: number,
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
    public assignedMembers: Map<string,IMember[]>,
    public startDate: Date,
    public plannedEndDate: Date,
    public endDate: Date,
    public resourceBudget: number,
    public resourceUsed: number,
    public status: string
  ) { }

}
