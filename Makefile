run-from-docker:
	docker run -p 3000:3000 -e DATABASE_URL=jdbc:h2:./guestbook_dev_docker.db -v $(CURDIR):/guestbook clojure /bin/bash -c 'cd /guestbook && lein run migrate && lein run'
