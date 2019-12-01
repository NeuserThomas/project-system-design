# Movie Flow Service

## Implementation

```
.
└── system_design
    └── project
        ├── MovieFlowServiceApplication.java
        └── adapters
            └── messaging
                ├── MessageGateway.java
                └── channels
                    ├── DomoticChannels.java
                    └── MovieChannels.java
```

### Adapters
#### Messaging
Channels are split into multiple interfaces to avoid cluttering.
- `DomoticChannels.java`: channels for domotic purposes, such as dimming the lights
- `MovieChannels.java`: channels for maintaining and supervising the different stages of a movie