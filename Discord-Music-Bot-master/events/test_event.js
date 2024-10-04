module.exports = {

name: "Test Custom Event",

isEvent: true,

fields: ["Temp Variable Name 1", "Temp Variable Name 2"],


mod: function(DBM) {

	DBM.Test_Event = DBM.Test_Event || {};

	DBM.Test_Event.callAllEvents = function() {
		// Grab them classes from the DBM namespace.
		const { Bot, Actions } = DBM;

		// Get all events that use this custom event trigger.
		const events = Bot.$evts["Test Custom Event"];

		// Ensure there are any.
		// If the user did not create any events with this trigger, this will be null.
		if(!events) return;

		// Call each one.
		for(let i = 0; i < events.length; i++) {
			const event = events[i];

			// Allocate the "temporary" variables object.
			const temp = {};

			if(event.temp) temp[event.temp] = 10;
			if(event.temp2) temp[event.temp2] = 20;

			const server = null;

			// Call the actions stored in the event.
			Actions.invokeEvent(event, server, temp);
		}
	};

}

}; 