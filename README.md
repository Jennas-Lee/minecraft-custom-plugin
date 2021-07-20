# minecraft-custom-plugin

![build status](https://travis-ci.org/Jennas-Lee/minecraft-custom-plugin.svg?branch=master)
![license](https://img.shields.io/github/license/Jennas-Lee/minecraft-custom-plugin)
![maven version](https://img.shields.io/badge/maven%20central-1.0.0-blue)

## Introduce

이 플러그인은 현재 제가 개인적으로 운영하는 마인크래프트 서버에서 사용합니다.

디스코드와 연결되어 서버 상태와 플레이어의 상태를 미리 볼 수 있으며,

이를 통해 소소한 재미도 유발합니다.

## Environment
- Paper 1.17.1
- Java 16(openjdk 16)
- Apache Maven
- IntelliJ IDEA Ultimate

## Feature
### Use Discord Bot

디스코드 봇을 개발해 해당 명령어로 플레이를 더욱 재밌게 만들어줍니다.

> `&list` : 현재 접속중인 서버의 플레이어를 조회합니다.

![&list](https://user-images.githubusercontent.com/55793046/126307019-3e216fcd-ddff-45ab-8e1e-fb6e7b61af28.png)

> `&status` : 서버의 현재 상태를 조회합니다.

![&status](https://user-images.githubusercontent.com/55793046/126307151-0dab9314-755b-4dea-a9fd-4a42db95fc02.png)

> `&help` : 도움말을 전송합니다.

![&help](https://user-images.githubusercontent.com/55793046/126307264-f59f9d9a-0745-42f5-a6f5-019eaf13aa6c.png)


### Prevent Using Sweet Berries

![sweet berries](https://static.wikia.nocookie.net/minecraft_gamepedia/images/1/12/Sweet_Berries_JE1_BE1.png/revision/latest/scale-to-width-down/160?cb=20190604204938)

개발자는 달콤한 열매를 매우 혐오합니다. ~~이상한 적개심을 품고 있습니다.~~

따라서 플러그인 차원으로 달콤한 열매를 **사용 불가능** 상태로 만들었습니다.

하지만, 다음의 명령어로 해제할 수 있습니다.

> `/preventsweetberries disable`

달콤한 열매의 차단을 중지합니다. **OP 권한이 필요합니다.**

> `/preventsweetberries enable`

달콤한 열매의 차단을 시작합니다. **OP 권한이 필요합니다.** 기본값입니다.