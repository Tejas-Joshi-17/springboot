
BUILD_FOLDER="build/install/notification-service/bin"

if [[ -d $BUILD_FOLDER ]]
then
  bash $BUILD_FOLDER/stop || exit 1;
  gradle clean iA
  bash $BUILD_FOLDER/start || exit 1;
  echo "waits for 5 sec"
  sleep 5s || exit
  less $BUILD_FOLDER/../logs/notification-service.log
else
  gradle clean iA
  # cd $BUILD_FOLDER || exit
  # bash $BUILD_FOLDER/start
fi