import { TestBed } from '@angular/core/testing';

import { ProjectTodoService } from './project-todo.service';

describe('ProjectTodoService', () => {
  let service: ProjectTodoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProjectTodoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
