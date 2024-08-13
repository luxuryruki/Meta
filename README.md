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
 2. `List of Instagram Graph API`
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
### Posting (Feed, Reels, Story)
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
### Message
