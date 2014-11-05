/* 
 * Copyright (C) 2014 erbjuder.com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.erbjuder.logger.server.entity.impl;

import com.erbjuder.logger.server.common.helper.DataBase;
import com.erbjuder.logger.server.common.helper.JSONPrettyPrintWriter;
import com.erbjuder.logger.server.entity.interfaces.LogMessageData;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import org.json.simple.JSONObject;

/**
 *
 * @author Stefan Andersson
 */
@Entity
@Table(name = "LogMessageData_Partition_12")
public class LogMessageData_Partition_12 implements Serializable, LogMessageData {

    @ManyToOne(targetEntity = LogMessage.class)
    private LogMessage logMessage;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PARTITION_12")
    @TableGenerator(name = "PARTITION_12",
            pkColumnName = "ENTITY", allocationSize = 10, table = "PK_ALLOCATE_SIZE_10")
    private Long id;
    @Column(columnDefinition = DataBase.LOGMESSAGEDATA_PARTITION_12_CONTENT_COLUMN_DEFINITION)
    protected String content;
    private String label;
    private String mimeType;
    private java.sql.Timestamp utcLocalTimeStamp;
    private java.sql.Timestamp utcServerTimeStamp;
    private boolean modified = false;
    private boolean searchable = true;
    private long contentSize;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public LogMessage getLogMessage() {
        return logMessage;
    }

    @Override
    public void setLogMessage(LogMessage logMessage) {
        this.logMessage = logMessage;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LogMessageData_Partition_12)) {
            return false;
        }
        LogMessageData_Partition_12 other = (LogMessageData_Partition_12) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.getId() != null ? this.getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean isModified() {
        return modified;
    }

    @Override
    public void setModified(boolean modified) {
        this.modified = modified;
    }

    @Override
    public boolean isSearchable() {
        return searchable;
    }

    @Override
    public void setSearchable(boolean searchable) {
        this.searchable = searchable;
    }

    @Override
    public long getContentSize() {
        return contentSize;
    }

    @Override
    public void setContentSize(long contentSize) {
        this.contentSize = contentSize;
    }

          @Override
    public Timestamp getUtcLocalTimeStamp() {
        return utcLocalTimeStamp;
    }

    @Override
    public void setUtcLocalTimeStamp(Timestamp utcLocalTimeStamp) {
        this.utcLocalTimeStamp = utcLocalTimeStamp;
    }

    @Override
    public Timestamp getUtcServerTimeStamp() {
        return utcServerTimeStamp;
    }

    @Override
    public void setUtcServerTimeStamp(Timestamp utcServerTimeStamp) {
        this.utcServerTimeStamp = utcServerTimeStamp;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String getMimeType() {
        return mimeType;
    }

    @Override
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    @Override
    public String toString() {
        return toJSONString();
    }

    @Override
    public String toJSONString() {
        return toJSON().toString();
    }

    @Override
    public String toJSONPrettyString() {
        String jsonString = "";
        try {
            JSONPrettyPrintWriter writer = new JSONPrettyPrintWriter();
            toJSON().writeJSONString(writer);
            jsonString = writer.toString();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return jsonString;
    }

    @Override
    public JSONObject toJSON() {

        JSONObject node = new JSONObject();
        node.put("id", this.getId());
        node.put("label", this.getLabel());
        node.put("mimeType", this.getMimeType());
        node.put("content", this.getContent());
        node.put("logMessage", this.getLogMessage().getId());
        return node;

    }

    @Override
    public int compareTo(LogMessageData o) {
        return this.label.compareToIgnoreCase(o.getLabel());
    }

}
