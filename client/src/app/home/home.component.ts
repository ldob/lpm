import { Component, OnInit } from '@angular/core';
import { UserService } from '../service/user.service';
import {AuthService} from "../service/auth.service";
import {TokenStorageService} from "../service/token-storage.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  title = 'GBF IT Projektmanagement';

  isLoggedIn = false;
  username: string = "";
  roles: string[] = [];

  content: string = "";

  constructor(private userService: UserService, private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;

      this.username = this.tokenStorage.getUser().username;
      this.roles = this.tokenStorage.getUser().roles;
    }
  }

}
