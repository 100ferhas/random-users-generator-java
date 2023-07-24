[![Publish to Maven Central](https://github.com/100ferhas/random-users-generator-java/actions/workflows/release.yml/badge.svg)](https://github.com/100ferhas/random-users-generator-java/actions/workflows/release.yml)

# Random Users Generator

This is a wrapper to use fake user APIs at https://randomuser.me/

### Installation

#### Maven
```xml
<dependency>
    <groupId>io.github.100ferhas</groupId>
    <artifactId>random-users-generator</artifactId>
    <version>1.1</version>
</dependency>
```

#### Gradle
```
implementation 'io.github.100ferhas:random-users-generator:1.1'
```

### Usage
Once the package is installed, you can import the provider class and start using it, see [examples](#examples).

```java
import io.github.ferhas.users_provider.client.UsersProvider;
```

[Response data model](#response-data) is the same as the REST API.

### Examples

Basic usage, just retrieve a single user without options:

```java
public class Example {
    
    public static void main(String[] args) {
        UsersProvider usersProvider = new UsersProvider();
        UsersProviderAPIResponse response = usersProvider.getUsers();

        // get results
        List<Result> results = response.getResults();

        // get infos
        Info info = response.getInfo();
    }
}
```

Alternatively, you can build a `UsersProviderAPIOptions` instance to provide options to retrieve data:

```java
public class Example {
    
    public static void main(String[] args) {
        UsersProviderAPIOptions options = UsersProviderAPIOptions
                .builder()
                .results(5)
                .build();

        UsersProviderAPIResponse response = new UsersProvider().getUsers(options);
    }
}
```

Or just pass to the method a `Supplier<UsersProviderAPIOptions>` that returns a `UsersProviderAPIOptions`:

```java
public class Example {
    
    public static void main(String[] args) {
        UsersProviderAPIResponse response = new UsersProvider().getUsers(
                () -> UsersProviderAPIOptions
                        .builder()
                        .results(5)
                        .build()
        );
    }
}
```

#### Request Options
| Parameter   | Type         | Description                                                          | Required | Allowed Values                                                                                                                                                       |
|-------------|--------------|----------------------------------------------------------------------|----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| noInfo      | Boolean      | if you want info attribute in the response                           | no       | `true` or `false`                                                                                                                                                        |
| results     | Integer      | number of results                                                    | no       | any integer                                                                                                                                                          |
| seed        | String       | a seed used to generate always same sets of users (useful for pages) | no       | any string                                                                                                                                                           |
| page        | Integer      | number of the page you are requesting                                | no       | any integer                                                                                                                                                          |
| gender      | APIGender       | gender of generated users                                            | no       | `'male' \| 'female'`                                                                                                                                                  |
| password    | UsersProviderPasswordAPIOptions | password generation policy                                           | no       | see [UsersProviderPasswordAPIOptions](#UsersProviderPasswordAPIOptions)                                                                                                                                                  |
| nationality | Set<APINationalities>             | nationality of generated users                                       | no       | `'AU' \| 'BR' \| 'CA' \| 'CH' \| 'DE' \| 'DK' \| 'ES' \| 'FI' \| 'FR' \| 'GB' \| 'IE' \| 'IN' \| 'IR' \| 'MX' \| 'NL' \| 'NO' \| 'NZ' \| 'RS' \| 'TR' \| 'UA' \| 'US'` |
| include     | Set<APIFields> | include fields in response                                           | no       | `'gender' \| 'name' \| 'location' \| 'email' \| 'login' \| 'registered' \| 'dob' \| 'phone' \| 'cell' \| 'id' \| 'picture' \| 'nat'`                                   |
| exclude     | Set<APIFields> | exclude fields in response                                           | no       | `'gender' \| 'name' \| 'location' \| 'email' \| 'login' \| 'registered' \| 'dob' \| 'phone' \| 'cell' \| 'id' \| 'picture' \| 'nat'`                                   |

#### UsersProviderPasswordAPIOptions
| Parameter | Type   | Description                                                | Required | Allowed Values                                |
|-----------|--------|------------------------------------------------------------|----------|-----------------------------------------------|
| charset   | Set<PasswordCharset> | what type of characters include in the generated password  | no       | `'special' \| 'upper' \| 'lower' \| 'number'` |
| minLength | Integer | min length of generated password                           | no       | any integer                                   |
| maxLength | Integer | max length of generated password                           | no       | any integer                                   |

#### Response Data
```json
{
  "results": [
    {
      "gender": "female",
      "name": {
        "title": "Miss",
        "first": "Jennie",
        "last": "Nichols"
      },
      "location": {
        "street": {
          "number": 8929,
          "name": "Valwood Pkwy"
        },
        "city": "Billings",
        "state": "Michigan",
        "country": "United States",
        "postcode": "63104",
        "coordinates": {
          "latitude": "-69.8246",
          "longitude": "134.8719"
        },
        "timezone": {
          "offset": "+9:30",
          "description": "Adelaide, Darwin"
        }
      },
      "email": "jennie.nichols@example.com",
      "login": {
        "uuid": "7a0eed16-9430-4d68-901f-c0d4c1c3bf00",
        "username": "yellowpeacock117",
        "password": "addison",
        "salt": "sld1yGtd",
        "md5": "ab54ac4c0be9480ae8fa5e9e2a5196a3",
        "sha1": "edcf2ce613cbdea349133c52dc2f3b83168dc51b",
        "sha256": "48df5229235ada28389b91e60a935e4f9b73eb4bdb855ef9258a1751f10bdc5d"
      },
      "dob": {
        "date": "1992-03-08T15:13:16.688Z",
        "age": 30
      },
      "registered": {
        "date": "2007-07-09T05:51:59.390Z",
        "age": 14
      },
      "phone": "(272) 790-0888",
      "cell": "(489) 330-2385",
      "id": {
        "name": "SSN",
        "value": "405-88-3636"
      },
      "picture": {
        "large": "https://randomuser.me/api/portraits/men/75.jpg",
        "medium": "https://randomuser.me/api/portraits/med/men/75.jpg",
        "thumbnail": "https://randomuser.me/api/portraits/thumb/men/75.jpg"
      },
      "nat": "US"
    }
  ],
  "info": {
    "seed": "56d27f4a53bd5441",
    "results": 1,
    "page": 1,
    "version": "1.4"
  }
}
```


Please feel free to make suggestion or PR to improve the client. **If you are using this library please star the repo for visibility.** 
