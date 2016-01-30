# Release process

----------

## set release version

    mvn versions:set -DnewVersion=0.7.1 -DgenerateBackupPoms=false

## create tag and commit

    git add .
    git commit -m "release 0.7.1"
    git tag 0.7.1
    git push --tags
    
## release

    mvn clean deploy -Prelease -Dmaven.test.skip=true

## set next snapshot version

    mvn versions:set -DnewVersion=0.7.2-SNAPSHOT -DgenerateBackupPoms=false
    git add .
    git commit -m "set next version"
    git push