
export interface IProjectStatus {

  id: number,
  date: Date,
  status: string,
  tweet: string,
  nextSteps: string,
  problems: string

}

export class ProjectStatus implements IProjectStatus {

  // @ts-ignore
  constructor()
  constructor(
    public id: number,
    public date: Date,
    public status: string,
    public tweet: string,
    public nextSteps: string,
    public problems: string
  ) { }

}
