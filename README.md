# Meta Graph API Usage and Method Implementations
 Welcome to the repository for exploring and archiving methods to interact with Meta's Graph API. This project is primarily for archival and educational purposes, focusing on how to use the Graph API and implement some of its methods.

## Table of Contents
- Introduction
- Features
- Usage Instructions
- List of Instagram Graph API

## Introduction
 This repository is a collection of scripts and documentation demonstrating how to interact with Meta's Graph API. It includes some method implementations and examples for educational and archival purposes. It is not intended for production use or as a comprehensive library.

## Features   
 1. Usage instructions   
 2. List of Instagram Graph API
- Authentication
- Posting (Feed, Reels, Story)
- Message

## Usage Instructions
### Instagram Platform API Type
**1. Instagram Basic Display API**  
 The Instagram Basic Display API allows app users to fetch basic profile information, photos, and videos from their Instagram accounts. This API is designed for Instagram users who are not businesses or creators.   
 ** Read Only   

**2. Instagram Graph API**   
 App users can access data from Instagram Business and Instagram Creator accounts connected to a Facebook Page. This API allows them to fetch and post media, manage and reply to comments on media, identify media in which other Instagram users are @mentioned, find media containing specific hashtags, and retrieve basic metadata and metrics for other Instagram Businesses and Creators.

### How to Use Instagram Graph API
1. Before start :
   1. Create a Facebook App for your software in the Meta Developer Portal. 
   2. Create a business or creator account on Instagram. 
   3. Create a Facebook Page linked to the Instagram account.
   
    To use the post and message functions, you need to log in via Facebook and obtain some information from the Facebook Page.
2. Login with Facebook
   1. You don't have to implement a Facebook UI page for login. Instead, you can use the [Graph API Explorer Tool](https://developers.facebook.com/tools/explorer/).
   2. When you log in, you need to grant the necessary authorizations for your intended purpose. Facebook will then return a token that you can use.
3. Get Long-Term Token (Optional)   
   If you don't need extended functions such as posting schedules or auto-response services, you can skip saving long-term tokens. However, users will need to log in each time they use the service.    
   For more details, please refer to the method `getLongTermToken` in [MetaAccountUtils.java]((src/main/java/com/example/meta/uitils/MetaAccountUtils.java)).
4. Get Page Information   
   To get Instagram Profile, you need facebook `pageId`.   
   For more details, please refer to the method `getPage` in [MetaAccountUtils.java]((src/main/java/com/example/meta/uitils/MetaAccountUtils.java)).
5. Get Instagram Profile Information   
   Finally, you are in the last step to use Instagram Graph API. Most API calls require `instagramId`.   
   For more details, please refer to the method `getProfile` in [MetaAccountUtils.java]((src/main/java/com/example/meta/uitils/MetaAccountUtils.java)).

## List of Instagram Graph API
### Authentication
**1. Get Long-Term Token**
**[URL]**  
```GET https://graph.facebook.com/v18.0/oauth/access_token```   

**[Parameters]**

|        Name         |   Type   |  Required  |   Default Value   |   Description   |
|:-------------------:|:--------:|:----------:|:-----------------:|:---------------:|
|     grant_type      |  String  |    true    | fb_exchange_token |   Grant Type    |
|      client_id      |  String  |    true    |        NA         |     App ID      |
|    client_secret    |  String  |    true    |        NA         | App Secret Code |
|  fb_exchange_token  |  String  |    true    |        NA         |   Valid Token   |

**[Response]**
```json
{
  "access_token": "EAAQVImp9AbwBO6324ladadfrttXj84oz6dVZA2ZBxgvVZCgRh4XtgkqcITV",
  "token_type": "bearer"
}
```

**2. Get Page**
**[URL]**  
```GET https://graph.facebook.com/v18.0/me/accounts```   

**[Parameters]**

|     Name     |   Type   |  Required  |   Default Value   |   Description    |
|:------------:|:--------:|:----------:|:-----------------:|:----------------:|
| access_token |  String  |    true    |        NA         |   Valid Token    |

**[Response]**
```json
{
  "data": [
    {
      "access_token": "EA4MWaJLaIOTmf8ISDAquv7i3tq46tZBReqBDV4hAZC7a08sfRvQLDjdywhgaj5PxqJAvkAQVImp9AbwBO4lkfdCxxiajgLmRmzX0ntQW2oFdTZBuK0PKHuYiG89UQKtHZBkZAaAj5FyzD7ylCbvVEb2f8bBhbjvJJspMEBZBjJRfaD8N5wm",
      "category": "software",
      "category_list": [
        {
          "id": "2211",
          "name": "소프트웨어"
        }
      ],
      "name": "dev.johnny",
      "id": "12353267",
      "tasks": [
        "ADVERTISE",
        "ANALYZE",
        "CREATE_CONTENT",
        "MESSAGING",
        "MODERATE",
        "MANAGE"
      ]
    },
    {
      "access_token": "EA4MWaJLaIOTmf8ISDAquv7i3tq46tZBReqBDV4hAZC7a08sfRvQLDjdywhgaj5PxqJAvkAQVImp9AbwBO4lkfdCxxiajgLmRmzX0ntQW2oFdTZBuK0PKHuYiG89UQKtHZBkZAaAj5FyzD7ylCbvVEb2f8bBhbjvJJspMEBZBjJRfaD8N5wm",
      "category": "internet marketing service",
      "category_list": [
        {
          "id": "123567",
          "name": "internet marketing service"
        }
      ],
      "name": "test",
      "id": "123456",
      "tasks": [
        "ADVERTISE",
        "ANALYZE",
        "CREATE_CONTENT",
        "MESSAGING",
        "MODERATE",
        "MANAGE"
      ]
    }
  ],
  "paging": {
    "cursors": {
      "before": "QVFIUkRpRTFaN25qT1dzYnpDMVZAHSEtWSk1wYjE5a2NlZA2p4YmVjCUU1BhVWDNPbHJ3S3dmbjdsSzE0ekZAISFlHTWZ1blJRNEhjZAXBrMl82ZA2VPT3dB",
      "after": "QcFlRSEzYnpDMVZAHSEtWSk1wYjE5a2NlZA2p4YmVjzYnpDMVZAHSEtWSk1wYjE5a2NlZA2p4YmVjs5MVNPd2NPWDFWlFhSjBxVTNXOE1R"
    }
  }
}
```

**3. Get Instagram ID**
**[URL]**  
```GET https://graph.facebook.com/v18.0/{{fbPageId}}```

**[Parameters]**

|     Name     |  Type   |  Required  |   Default Value   |          Description          |
|:------------:|:-------:|:----------:|:-----------------:|:-----------------------------:|
|  fbPageId    | Integer |    true    |        NA         |       facebook page id        |
|    fields    | String  |    true    |        NA         | fields of data to be returned |
| access_token | String  |    true    |        NA         |          Valid Token          |

**[Response]**
```json
{
  "instagram_business_account": {
    "id": "123456788"
  },
  "name": "johnny.dev",
  "category": "software",
  "id": "12346567"
}
```

**4. Get Instagram Profile**
**[URL]**  
```GET https://graph.facebook.com/v18.0/{{igId}}```
**[Parameters]**

|     Name     |  Type   |  Required  |   Default Value   |          Description          |
|:------------:|:-------:|:----------:|:-----------------:|:-----------------------------:|
|     igId     | Integer |    true    |        NA         |         instagram id          |
|    fields    | String  |    true    |        NA         | fields of data to be returned |
| access_token | String  |    true    |        NA         |          Valid Token          |

**[Response]**
```json
{
  "username": "dev.johnny",
  "business_discovery": {
    "username": "dev.johnny",
    "media_count": 29,
    "followers_count": 2200,
    "follows_count": 0,
    "id": "17841463105660648"
  },
  "profile_picture_url": "https://scontent-ssn1-1.xx.fbcdn.net/v/asdf.2885-15/adsf.jpg?_nc_cat=asfd&ccb=1-7&_nc_sid=7d201b&_nc_ohc=UDQAiReC04IQ7kNvgFkc8Ut&_nc_ht=scontent-ssn1-1.xx&edm=AL-3X8kEAAAA&oh=00_AYCMiuB1oaeFRECOzyVaum2kYRXpZ_3fGLPrXdKEgZSJ6A&oe=66C1D507",
  "id": "12345678"
}
```
### Contents (Feed, Reels, Story)
**1. get Media**
**[URL]**  
```GET https://graph.facebook.com/v18.0/{{igId}}/media```
**[Parameters]**

|     Name     |  Type   | Required |   Default Value   |          Description           |
|:------------:|:-------:|:--------:|:-----------------:|:------------------------------:|
|     igId     | Integer |    true  |        NA         |       instagram id             |
|    fields    | String  |   true   |        NA         | fields of data to be returned  |
| access_token | String  |   true   |        NA         |          Valid Token           |
|    limit     | Integer |   true   |        NA         | number of media to be returned |
|    before    | String  |  false   |        NA         |         previous page          |
|    after     | String  |  false   |        NA         |           next page            |

**[Response]**
```json
{
  "data": [
    {
      "id": "1234",
      "media_type": "VIDEO",
      "media_url": "https://scontent-ssn1-1.cdninstagram.com/o1/v/t16/f1/m86/asdfasdf.mp4?ef6&_nc_sid=1d576d",
      "permalink": "https://www.instagram.com/reel/C-adfasdf/",
      "media_product_type": "REELS"
    },
    {
      "id": "2135",
      "media_type": "IMAGE",
      "media_url": "https://scontent-ssn1-1.cdninstagram.com/v/t39.30808-6/414429164_17850794973103523_6141870855178044110_n.wN-g&oe=66C1C539",
      "permalink": "https://www.instagram.com/p/C1Y2T55yQZb/",
      "media_product_type": "FEED"
    },
    {
      "id": "12321",
      "media_type": "CAROUSEL_ALBUM",
      "media_url": "https://scontent-ssn1-1.cdninstagram.com/v/t39.30808-6/401ninstag",
      "media_product_type": "FEED"
    }
  ],
  "paging": {
    "cursors": {
      "before": "QVaASDFeoFIUlhSRVJYSHBtdGtqTl9ZAZAWasdfasfda2RQYWw0XzlGeGlB",
      "after": "QVFIUnpFZAASDFeoasdEgfasoeJcXNIdUhEaHlWSW9ZAdmVqQThhbVRqRnpEcHM5bDBR"
    }
  }
}
```
**2. get Album(Children) Media**
**[URL]**  
```GET https://graph.facebook.com/v18.0/{{mediaId}}/chidren```
**[Parameters]**

|     Name     |  Type   | Required |   Default Value   |          Description          |
|:------------:|:-------:|:--------:|:-----------------:|:-----------------------------:|
|   mediaId    | Integer |   true   |        NA         |        media album id         |
|    fields    | String  |   true   |        NA         | fields of data to be returned |
| access_token | String  |   true   |        NA         |          Valid Token          |


**[Response]**
```json
{
  "data": [
    {
      "id": "1234",
      "media_type": "VIDEO",
      "media_url": "https://scontent-ssn1-1.cdninstagram.com/o1/v/t16/f1/m86/asdfasdf.mp4?ef6&_nc_sid=1d576d",
      "permalink": "https://www.instagram.com/reel/C-adfasdf/",
      "media_product_type": "REELS"
    },
    {
      "id": "2135",
      "media_type": "IMAGE",
      "media_url": "https://scontent-ssn1-1.cdninstagram.com/v/t39.30808-6/414429164_17850794973103523_6141870855178044110_n.wN-g&oe=66C1C539",
      "permalink": "https://www.instagram.com/p/C1Y2T55yQZb/",
      "media_product_type": "FEED"
    },
    {
      "id": "12321",
      "media_type": "CAROUSEL_ALBUM",
      "media_url": "https://scontent-ssn1-1.cdninstagram.com/v/t39.30808-6/401ninstag",
      "media_product_type": "FEED"
    }
  ],
  "paging": {
    "cursors": {
      "before": "QVaASDFeoFIUlhSRVJYSHBtdGtqTl9ZAZAWasdfasfda2RQYWw0XzlGeGlB",
      "after": "QVFIUnpFZAASDFeoasdEgfasoeJcXNIdUhEaHlWSW9ZAdmVqQThhbVRqRnpEcHM5bDBR"
    }
  }
}
```

**3. posting**   
**post single content**    
3-1-1 create single container    
**[URL]**  
```GET https://graph.facebook.com/v18.0/{{igId}}/media```
**[Parameters]**

|     Name     |  Type   | Required |   Default Value   |    Description    |
|:------------:|:-------:|:--------:|:-----------------:|:-----------------:|
|     igId     | Integer |   true   |        NA         |   instagram id    |
| access_token | String  |   true   |        NA         |    Valid Token    |
|  media_type  | String  |   true   |        NA         | should be 'IMAGE' |
|  image_url   | String  |   true   |        NA         |     image url     |
|  user_tags   | String  |   true   |        NA         |     user tage     |
|   caption    | String  |   true   |        NA         |      caption      |

**[Request]**
```
{
    "access_token": "asdf32523sadfSAdfienG3oiqwpoeifnlksfa",
    "media_type": "IMAGE",
    "image_url": "https://www.image.com/image1.jpg",
    "caption": "sigle content upload",
    "user_tags": [
        {
            "username":"johnny",
            "x" : 0.2,
            "y" :0.7
        }
        {
            "username":"johnny2",
            "x" : 0.3,
            "y" :0.7
        }
    ]
}
```

**[Response]**
```
{
    "id": "112388319"
}
```
3-1-2 post single container   
**[URL]**  
```GET https://graph.facebook.com/v18.0/{{igId}}/media_publish```
**[Parameters]**

|     Name     |  Type   | Required |   Default Value   |     Description     |
|:------------:|:-------:|:--------:|:-----------------:|:-------------------:|
|     igId     | Integer |   true   |        NA         |    instagram id     |
| access_token | String  |   true   |        NA         |     Valid Token     |
| creation_id  | String  |   true   |        NA         | single container Id |

**[Response]**
```
{
    "id": "352388319"
}
```
**post multiple content**   
3-2-1 create single container   
**[URL]**  
```GET https://graph.facebook.com/v18.0/{{igId}}/media```
**[Parameters]**

|       Name       |  Type   | Required |   Default Value   |      Description       |
|:----------------:|:-------:|:--------:|:-----------------:|:----------------------:|
|       igId       | Integer |   true   |        NA         |      instagram id      |
|   access_token   | String  |   true   |        NA         |      Valid Token       |
|    media_type    | String  |   true   |        NA         |   'IMAGE' or 'VIDEO'   |
|    image_url     | String  |   true   |        NA         |       image url        |
|   video_url      | String  |   true   |        NA         |       video url        |
| is_carousel_item | Boolean |  false   |        NA         | if it is album in feed |


**[Response]**
```
{
    "id": "112388319"
}
```
3-2-2 create slide container   
**[URL]**  
```GET https://graph.facebook.com/v18.0/{{igId}}/media```
**[Parameters]**

|     Name     |  Type   | Required |   Default Value   |  Description   |
|:------------:|:-------:|:--------:|:-----------------:|:--------------:|
|     igId     | Integer |   true   |        NA         |  instagram id  |
| access_token | String  |   true   |        NA         |  Valid Token   |
|  media_type  | String  |   true   |        NA         |   'CAROUSEL'   |
|   children   |  ARRAY  |   true   |        NA         | Container ids  |
|   caption    | String  |   true   |        NA         |    caption     |
**[Response]**
```
{
    "id": "112388319"
}
```
3-2-3 post slide container   
**[URL]**  
```GET https://graph.facebook.com/v18.0/{{igId}}/media_publish```
**[Parameters]**

|     Name     |  Type   | Required |   Default Value   |    Description     |
|:------------:|:-------:|:--------:|:-----------------:|:------------------:|
|     igId     | Integer |   true   |        NA         |    instagram id    |
| access_token | String  |   true   |        NA         |    Valid Token     |
| creation_id  | String  |   true   |        NA         | slide container Id |

**[Response]**
```
{
    "id": "352388319"
}
```

4 check container status
**[URL]**

```GET https://graph.facebook.com/v18.0/{{instagramContainerId}}/media_publish```
   
**[Parameters]**

|         Name         |  Type   | Required | Default Value |    Description    |
|:--------------------:|:-------:|:--------:|:-------------:|:-----------------:|
| instagramContainerId | Integer |   true   | container Id  |   instagram id    |
|     access_token     | String  |   true   |      NA       |    Valid Token    |
|        fields        | String  |   true   |      NA       | 'status_code,id'  |
**[Response]**
```
{
    "status_code": "FINISHED",
    "id": "18032008459976315"
}
```
### Message
**1. get page access token**   
**[URL]**  
```GET https://graph.facebook.com/v18.0/{{fbPageId}}```
**[Parameters]**

|     Name     |  Type   | Required |   Default Value   |  Description   |
|:------------:|:-------:|:--------:|:-----------------:|:--------------:|
|   fbPageId   | Integer |   true   |        NA         |  facebook id   |
| access_token | String  |   true   |        NA         |  Valid Token   |
|    fields    | String  |   true   |        NA         | 'access_token' |

**[Response]**
```
{
    "access_token": "BreQV3319AbwBOy7tasdfsaReMKv9ZC36lZCXrasdfs1gXXaGogjk4cTLmZAZAZCtl8Qz15dsfgu2asdfpsKAt2",
    "id": "123456789"
}
```


**2. get conversations**   
**[URL]**  
```GET https://graph.facebook.com/v18.0/{{fbPageId}}/conversations```
**[Parameters]**

|     Name     |  Type   | Required |   Default Value   | Description |
|:------------:|:-------:|:--------:|:-----------------:|:-----------:|
|   fbPageId   | Integer |   true   |        NA         | facebook id |
| access_token | String  |   true   |        NA         | Valid Token |
|   platform   | String  |   true   |        NA         | 'instagram' |

**[Response]**
```
{
    "data": [
        {
            "id": "a1gsdfhgjy2ODQxNzEaGacvGTwefgJhiUaDc0NzU5KDIg26dVAASD",
            "updated_time": "2024-01-31T00:51:30+0000"
        }
    ]
}
```
**3. get messages in conversation**
**[URL]**  
```GET https://graph.facebook.com/v18.0/{{chatRoomId}}```
**[Parameters]**

|     Name     |  Type   | Required |   Default Value   |   Description   |
|:------------:|:-------:|:--------:|:-----------------:|:---------------:|
|  chatRoomId  | Integer |   true   |        NA         | conversation id |
| access_token | String  |   true   |        NA         |   Valid Token   |
|   fields     | String  |   true   |        NA         |   'messages'    |

**[Response]**
```
{
    "messages": {
        "data": [
            {
                "id": "aWcxMTI0NDI1OTQ3NDc1OTU0ODUwOTAQ3NDc1OzMyNDU2ODY5MDY4OAZDZDDk2OTQwMTQyMDY3NjcxODg3NDQ4MjE4MDA5NgZDMw",
                "created_time": "2024-01-31T00:51:30+0000"
            },
            {
                "id": "a2Njg0M1NzozMTQ2MTc4MMTI0NDI1OTTcxMDMwMD4NjQ0NDMxNxOklHTWVOjE3ODQxNDYzMTAjQ4OjM0MDI4MjMMZD1ZD",
                "created_time": "2024-01-18T02:56:03+0000",
                "is_unsupported": true
            },
            {
                "id": "aWdfZAG1M2MjQ5NTgxNTkNjYwNfaXRlbToTU0ODUwOTA1NzozMTQ4MjlEcxMZDjI0NDI1OTQOjQ4OjM0MDI4MjMMZDDg3NDQ4M",
                "created_time": "2024-01-17T09:53:06+0000",
                "is_unsupported": true
            },
            {
                "id": "ag0MTzc2FnZAUI0NDI1OTQ3NDc1OTU0ODUwOTAQ3NDc1OzMyNDU2ODY5MDY4OAZDZDDk2OTQwMTQyMDY3NjcxODg3NDQ4Mj",
                "created_time": "2024-01-17T09:37:52+0000",
                "is_unsupported": true
            }
        ],
        "paging": {
            "cursors": {
                "after": "Z2FnZAUI0NDI1OTQ3NDc1OTU0ODUwOTAQ3NDc1OzMyNDU2ODY5MDY4OAZDZDDk2OTAD"
            },
            "next": "https://graph.facebook.com/v18.0/aWdfZ2FnZAUI0NDI1OTQ3NDc1OTU0ODUA5MDU3/messages?access_token=EA2FnZAUI0NDI1OTQ3NDc1OTU0ODUwOTAQ3NDc1OzMyNDU2ODY5MDY4OAZDZDDk2OTD"
        }
    },
    "id": "aW2FnZAUI0NDI1OTQ3NDc1OTU0ODUwOTAQ3NDc1OzMyNDU2ODY5MDY4OAZDZDDk2OTTA5MDU3"
}
```


**4. get message detail with id**
**[URL]**  
```GET https://graph.facebook.com/v18.0/{{chatMessageId}}```

**[Parameters]**

|     Name      |  Type   | Required |   Default Value   |            Description            |
|:-------------:|:-------:|:--------:|:-----------------:|:---------------------------------:|
| chatMessageId | Integer |   true   |        NA         |         chatMessageId id          |
| access_token  | String  |   true   |        NA         |            Valid Token            |
|    fields     | String  |   true   |        NA         | 'id,created_time,from,to,message' |

**[Response]**
```
{
    "id": "aasdfY123489bs;asjkfbaskasD",
    "created_time": "2024-01-31T00:51:30+0000",
    "from": {
        "username": "johnnyddd",
        "id": "12356890-0"
    },
    "to": {
        "data": [
            {
                "username": "dev.flyer",
                "id": "12345789"
            }
        ]
    },
    "message": "test message"
}
```

**5. send message**
**[URL]**  
```GET https://graph.facebook.com/v18.0/me/messages```

**[Parameters]**

|     Name      |  Type   | Required |   Default Value   |            Description            |
|:-------------:|:-------:|:--------:|:-----------------:|:---------------------------------:|
| access_token  | String  |   true   |        NA         |            Valid Token            |

**[Body]**
```
{
    "recipient": {
        "id": 123455 //user id
    },
    "message":{
        "text":"test" // message
    }
}
```

**[Response]**
```
{
    "recipient_id": "123455",
    "message_id": "aWdfZAG1faXE5MzUzNgfasdfi2Asdfoo2mZDZD"
}
```
