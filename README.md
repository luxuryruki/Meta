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

|        Name         |   Type   |  Required  |   Default Value   |    Description    |
|:-------------------:|:--------:|:----------:|:-----------------:|:-----------------:|
|     grant_type      |  String  |    true    | fb_exchange_token |   Grant Type    |
|      client_id      |  String  |    true    |        NA         |     App ID      |
|    client_secret    |  String  |    true    |        NA         | App Secret Code |
|  fb_exchange_token  |  String  |    true    |        NA         |  Valid Token  |

**[Response]**
```json
{
  "access_token": "EAAQVImp9AbwBO6324ladadfrttXj84oz6dVZA2ZBxgvVZCgRh4XtgkqcITV",
  "token_type": "bearer"
}
```

**2. Get Page**

**3. Get Instagram Profile**
### Posting (Feed, Reels, Story)
### Message
