# ROADMAP

### Backend
- RetrievalService to retrieve OHLC data
- StrategyService (Implement as an interface for reusability)
    - Factory pattern to reduce code duplication
    - Algo calculation + performance metric calculation
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

