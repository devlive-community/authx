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
import { Response } from '@angular/http';

import { CommonResultModel } from '../../shared/model/result/result.model';

export class ResultUtils {

    public static extractData(res: Response): CommonResultModel {
        const body = res.json();
        const commonResponseModel: CommonResultModel = new CommonResultModel();
        if (body) {
            commonResponseModel.code = body.code;
            commonResponseModel.msg = body.message;
            commonResponseModel.data = body.data;
        }
        return commonResponseModel;
    }

    public static getError(result) {
        if (!result) {
            return null;
        }
        let errors = '';
        result.data.error.forEach(e => {
            errors = e.message + '\n';
        });
        // tslint:disable-next-line:max-line-length
        return 'This submission form appears altogether ' + result.data.count + ' second error, the following is the details of the error: \n'
            + errors;
    }

}
