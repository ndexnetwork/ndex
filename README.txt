----
# nDEX Network | A Blockchain Business Development Platform #
[![Docker Stars](https://img.shields.io/docker/stars/ndexnetwork/ndex.svg)](https://hub.docker.com/r/ndexnetwork/ndex?style=flat)
[![Docker Pulls](https://img.shields.io/docker/pulls/ndexnetwork/ndex.svg)](https://hub.docker.com/r/ndexnetwork/ndex?style=flat)
![Docker Cloud Build Status](https://img.shields.io/docker/cloud/build/ndexnetwork/ndex?label=nDEX%20%7C%20docker%20build&style=flat)
![GitHub release (latest by date)](https://img.shields.io/github/v/release/ndexnetwork/ndex?style=flat)

----
## What is NDX? ##
NDX is a cryptocurrency to make the world a better place.
See [LICENSE](https://raw.githubusercontent.com/ndexnetwork/nDEX/master/.github/LICENSE)

----

### PORTS AND DETAILS
--------------------
Name of Blockchain: nDEX

Peer port: 6899

Testnet peer port: 6898

API server port: 6868

Website of the project: [nDEX Network's NDX Page](https://ndx.ndexnetwork.com)

### Run in Docker [Easy like 1,2,3 ]
```
1. Install docker : sudo curl -sSL https://get.docker.com/ | sh
2. Install docker-compose & change its permission:
sudo curl -L https://github.com/docker/compose/releases/download/1.24.1/docker-compose-`uname -s`-`uname -m` -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose
3. Finally run:
docker run --name ndex -d -p 80:6868 ndexnetwork/ndex
[ No deps no fixer!!!]
```

### Open JDK works FINE!!
----
### Run it! ##

  - click on the NDX icon, or start from the command line:
  - linux: `./start.sh`
  - Window: `run.bat`

  - wait for the JavaFX wallet window to open
  - on platforms without JavaFX, open http://localhost:6868/ in a browser

----
### Compile it! ##

  - if necessary with: `./compile.sh`
  - you need jdk-8 as well [Use openjdk (Linux users)]

----
## Troubleshooting the NDX Server ##

  - How to Stop the NDX Server?
    - click on NDX Stop icon, or run `./stop.sh`
    - or if started from command line, ctrl+c or close the console window

  - UI Errors or Stacktraces?
    - report on Github

  - Permissions Denied?
    - no spaces and only latin characters in the path to the NRS installation directory
    - known jetty issue

----
### Further Reading ##

  - in this repository:
    - USERS-GUIDE.md
    - DEVELOPERS-GUIDE.md
    - OPERATORS-GUIDE.md
    - In the doc folder

----

```
                               Copyright © 2020 nDEX Network Ltd.
                             Copyright © 2016-2018 Jelurida IP B.V.

```
