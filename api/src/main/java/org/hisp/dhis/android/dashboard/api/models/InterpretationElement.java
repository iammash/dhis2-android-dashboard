/*
 * Copyright (c) 2015, dhis2
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 *  Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.hisp.dhis.android.dashboard.api.models;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ForeignKeyAction;
import com.raizlabs.android.dbflow.annotation.ForeignKeyReference;
import com.raizlabs.android.dbflow.annotation.NotNull;
import com.raizlabs.android.dbflow.annotation.Table;

import org.hisp.dhis.android.dashboard.api.models.meta.DbDhis;

/**
 * @author Araz Abishov <araz.abishov.gsoc@gmail.com>.
 *         <p/>
 *         This model class is intended to represent content of Interpretation {map, chart,
 *         reportTable, dataSet, period, organisationUnit}
 */
@Table(databaseName = DbDhis.NAME)
public final class InterpretationElement extends BaseIdentifiableObject {
    public static final String TYPE_CHART = "CHART";
    public static final String TYPE_MAP = "MAP";
    public static final String TYPE_REPORT_TABLE = "REPORT_TABLE";
    public static final String TYPE_DATA_SET = "DATASET_REPORT";
    public static final String TYPE_PERIOD = "PERIOD";
    public static final String TYPE_ORGANISATION_UNIT = "ORGANISATION_UNIT";

    @Column
    @NotNull
    String type;

    @Column
    @ForeignKey(
            references = {
                    @ForeignKeyReference(columnName = "interpretation",
                            columnType = long.class, foreignColumnName = "id")
            }, saveForeignKeyModel = false, onDelete = ForeignKeyAction.CASCADE
    )
    Interpretation interpretation;

    public InterpretationElement() {
        // empty constructor
    }

    /**
     * Factory method which allows to create InterpretationElement
     * by using DashboardElement as main source of data.
     *
     * @param interpretation   Interpretation to which we will assign interpretation element
     * @param dashboardElement DashboardElement from which we want to create interpretation element.
     * @return new InterpretationElement
     */
    public static InterpretationElement fromDashboardElement(Interpretation interpretation,
                                                             DashboardElement dashboardElement,
                                                             String mimeType) {
        InterpretationElement interpretationElement = new InterpretationElement();
        interpretationElement.setUId(dashboardElement.getUId());
        interpretationElement.setName(dashboardElement.getName());
        interpretationElement.setDisplayName(dashboardElement.getDisplayName());
        interpretationElement.setCreated(dashboardElement.getCreated());
        interpretationElement.setLastUpdated(dashboardElement.getLastUpdated());
        interpretationElement.setAccess(dashboardElement.getAccess());
        interpretationElement.setType(mimeType);
        interpretationElement.setInterpretation(interpretation);
        return interpretationElement;
    }

    public Interpretation getInterpretation() {
        return interpretation;
    }

    public void setInterpretation(Interpretation interpretation) {
        this.interpretation = interpretation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
