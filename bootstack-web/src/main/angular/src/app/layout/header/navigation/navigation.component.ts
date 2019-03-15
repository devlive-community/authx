import {animate, Component, OnInit, state, style, transition, trigger} from '@angular/core';
import {SharedService} from "../../../shared/services/shared.service";

@Component({
    selector: 'bootstack-navigation',
    templateUrl: './navigation.component.html',
    styleUrls: ['./navigation.component.scss'],
    animations: [
        trigger('toggleHeight', [
            state('inactive', style({
                height: '0',
                opacity: '0'
            })),
            state('active', style({
                height: '*',
                opacity: '1'
            })),
            transition('inactive => active', animate('200ms ease-in')),
            transition('active => inactive', animate('200ms ease-out'))
        ])
    ]
})

export class NavigationComponent implements OnInit {

    user;

    sidebarVisible: boolean;

    // Sub menu visibilities
    navigationSubState: any = {
        Tables: 'inactive',
        Forms: 'inactive',
        SamplePages: 'inactive',
        UserInterface: 'inactive',
        Components: 'inactive',
        Charts: 'inactive',
    };

    // The secondary menu
    toggleNavigationSub(menu, event) {
        event.preventDefault();
        this.navigationSubState[menu] = (this.navigationSubState[menu] === 'inactive' ? 'active' : 'inactive');
    }

    constructor(private sharedService: SharedService) {
        sharedService.sidebarVisibilitySubject.subscribe((value) => {
            this.sidebarVisible = value;
        })
    }

    ngOnInit() {
        // this.user = JSON.parse(sessionStorage.getItem(CommonConfig.AUTH_USER));
    }

    logout() {
        // UserLoginComponent.logout();
    }

}
