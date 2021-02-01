import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-member-input-dialog',
  templateUrl: './member-input-dialog.component.html',
  styleUrls: ['./member-input-dialog.component.css']
})
export class MemberInputDialogComponent implements OnInit {

  title: string;
  closeText: string;
  saveText: string;

  username: string;

  constructor(private dialogRef: MatDialogRef<MemberInputDialogComponent>, @Inject(MAT_DIALOG_DATA) data: any) {
    this.title = data.title;
    this.closeText = data.close;
    this.saveText = data.save;

    this.username = '';
  }

  ngOnInit() {
  }

  save() {
    this.dialogRef.close(this.username);
  }

  close() {
    this.dialogRef.close();
  }

}
