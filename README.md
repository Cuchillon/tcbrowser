#### Training project for testing GUI three ways - locally, with testContainers or with Kubernetes

Tests can be run either on local PC, or using testContainers, or via CI/CD tool using remote selenium server.

Parameters can be set via gradle (-Pname=value) or in src/main/resources/local.properties. Passed via gradle have more priority.

###### Parameters:
* ui.base.url - *URL of application under testing*
* selenium.server.host
* selenium.server.port
* selenium.server.path
* test.env.type - *Environment where to run tests - 'test-containers' or 'remote'*
* browser.type - *Browser type - 'chrome' or 'firefox'*
* vnc.record.mode - *TestContainers VNC video records settings - 'skip', 'all' or 'fail'*
* vnc.record.path
* test.user.auth
* test.user.password