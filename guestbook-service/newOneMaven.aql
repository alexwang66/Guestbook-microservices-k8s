items.find({
    "repo":"libs-release-local",
    "name":{"$match":"guestbook-service-*.jar"},
    "@unittest.summary.pass_rate":{"$eq":"100"},
    "@test.approve":{"$eq":"true"}
}).sort({
    "$desc": ["created"]
}).limit(1)
