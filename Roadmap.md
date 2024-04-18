# ROADMAP

### Backend
- RetrievalService to retrieve OHLC data
- StrategyService (Implement as an interface for reusability)
    - Registry pattern to manage instances of injected dependencies
    - Algo calculation + performance metric calculation (Is there a need to further abstract the classes)
    - Take into account leverage
    - DCA, ValueAvg, EMA crossovers
- NotificationService
    - Email 
    - Telegram bot
- Different profile
    - Guest
    - User
    - Admin
- logging in via socials to save watchlist (For user profile)
    - Link to google, discord
    - Find out how to do OTP and OAuth

### Frontend
- Sync values back to lightweight-charts
    - Use tanstack-query for client-side state mgmt
    - implement light/dark mode
- Manage watchlist

### Possible considerations
- convert OHLCvalues into an object class to decrease space used

