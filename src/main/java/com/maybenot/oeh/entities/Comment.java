package com.maybenot.oeh.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Data;

import java.util.Date;

@DynamoDBTable(tableName = "comments")
@Data
public class Comment {

    @DynamoDBHashKey(attributeName = "commentId")
    @DynamoDBAutoGeneratedKey
    private String commentId;

    @DynamoDBAttribute(attributeName = "userId")
    private String userId;

    @DynamoDBAttribute(attributeName = "placeId")
    private String placeId;

    @DynamoDBAttribute(attributeName = "text")
    private String text;

//    @DynamoDBAttribute(attributeName = "createdAt")
//    private Date createdAt;
}