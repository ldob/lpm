export interface Project {

  id: number,
  name: string,
  description: string,
  priority: string,
  assignedMembers: any,
  startDate: Date,
  plannedEndDate: Date,
  endDate: Date,
  resourceBudget: number,
  status: string

}
