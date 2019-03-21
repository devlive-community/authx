/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { Subscription } from 'rxjs/Subscription';
import { FormGroup, FormControl } from '@angular/forms';
import { ToastyService } from 'ng2-toasty';

import { CookieUtils } from '../../shared/utils/cookie.util';

import { SharedService } from '../../shared/services/shared.service';
import { UserService } from '../../../services/user/user.service';

import { UserParam } from '../../shared/param/user/user.param';
import { CommonConfig } from '../../../config/common.config';
import { CodeConfig } from '../../../config/code.config';
import { ResponseUtils } from '../../shared/utils/response.util';
import { UserModel } from '../../shared/model/user/user.model';

@Component({
  selector: 'bootstack-header',
  templateUrl: './header.component.html'
})
export class HeaderComponent implements OnInit {

  token: String;
  public userInfo;
  maThemeModel = 'green';

  setTheme() {
    this.sharedService.setTheme(this.maThemeModel);
  }

  constructor(private sharedService: SharedService,
    private router: Router,
    private userService: UserService,
    private toastyService: ToastyService) {
    sharedService.maThemeSubject.subscribe((value) => {
      this.maThemeModel = value;
    });
  }

  ngOnInit() {
    this.token = CookieUtils.get();
  }

}
