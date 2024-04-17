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
- Different users
    - Guest
    - Logged-in user
    - Admin

### Frontend
- Sync values back to lightweight-charts
    - Use tanstack-query for client-side state mgmt
    - implement light/dark mode
- Manage watchlist

### Possible considerations
- convert OHLCvalues into an object class to decrease space used
- OAuth to allow logging in to save watchlist
    - Link to google, discord

