# camel-openshift

Camel component to manage the OpenShift platform. 

## URI Format

    openshift://operation[?options]

Where **operation** represents the requested resource.

## Authentication

Every invocation must authenticate with the OpenShift platform. Credentials can be provided as URI options, message headers or directly on the OpenShift component (*Note*: Headers take precedence)

## OpenShiftComponent

The OpenShift Component can be configured with the OpenShift authentication parameters. These parameters can be configured directly on the endpoint:

| Option   |      Description     |
|:----------|:-------------|
| userName |  The username |
| password |  The password |


## Resource Retrival

The following are operations that can be made to retrieve content from the OpenShift platform

### User: 

| Operation   |      Description     |
|:----------|:-------------|
| user |  View User |

### Domain

| URI   |      Description     |
|:----------|:-------------|
| domains |  View All Domains |
| domain/domainId |  View Domain |

### Application:

| URI   |      Description     |
|:----------|:-------------|
| applications/domainId |  View All Applications in a Domain |

## Resource Invocation

The following are operations which can be used to modify the state of the OpenShift platform

### Domain

| URI   |      Description     |
|:----------|:-------------|
| domain/domainId/delete |  Delete Domain |
| domain/domainId/createapplication |  Create Application in Domain |

### Application

| URI   |      Description     |
|:----------|:-------------|
| application/appUUID/start |  Start Application |
| application/appUUID/stop|  Stop Application |
| application/appUUID/restart |  Restart Application |
| application/appUUID/delete |  Delete Application |

## Message Header

The following are a set of headers which can be used to pass required parameters:

| Name   |      Description     |
|:----------|:-------------|
|OpenShiftUserName|The name of the user account in which to authenticate|
|OpenShiftPassword|The password of the user account in which to authenticate|


## Input Parameters

Creation of new resources is facilitated by specifying the appropriate resource and passing parameters in the `body` of the camel message. The following describes the parameters required for the specific resource:

| Type   |      Body Type     | Parameters |
|:----------|:-------------|:-------------|
|Application Creation|Map|<ul><li>OpenShiftNewApplicationName - Name of the new Application</li><li>OpenShiftNewApplicationCartridge - id of the Standalone Cartridge</li></ul>|
|Domain Creation| String or Map|Can be provided as a String or Map. If Map provided:<ul><li>OpenShiftNewDomainId - Id of the new Domain</li></ul>|



## Use Cases

Retrieve User Details

`openshift://user?userName=openshiftuser&password=password`

View Domains beloning to a user

`openshift://domains?userName=openshiftuser&password=password`

View Applications belonging to a user

`openshift://applications?userName=openshiftuser&password=password`

View a specific Application

`openshift://application/applicationUUID?userName=openshiftuser&password=password`

Restart an Application

`openshift://application/applicationUUID/restart?userName=openshiftuser&password=password`