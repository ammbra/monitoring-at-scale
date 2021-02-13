#!/usr/bin/env bash

while :
do
    declare -a Actions=('buy/itinerary' 'refund' 'buy/itinerary' 'buy/event' 'buy/event' 'refund' 'buy/itinerary' 'refund' 'buy/event' );

    actionName=`echo ${Actions[$[ RANDOM % ${#Actions[@]} ]]}`

    echo "Executing action $actionName"

    curl -k $ORDER/$actionName

    sleep 5
done

